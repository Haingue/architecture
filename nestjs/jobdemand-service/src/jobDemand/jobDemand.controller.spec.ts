// job-demand.controller.spec.ts
import { Test, TestingModule } from '@nestjs/testing';
import { JobDemandService } from './JobDemand.service';
import {
  NotFoundException,
  UnauthorizedException,
  BadRequestException,
} from '@nestjs/common';
import { JobDemandController } from './jobDemand.controller';
import { Student } from './entities/student.entity';
import { Speciality } from './entities/speciality.entity';
import { JobDemand } from './entities/jobDemand.entity';

describe('JobDemandController', () => {
  let controller: JobDemandController;
  let service: JobDemandService;

  // Mock data aligned with the real entity model
  const mockStudents: Student[] = [
    {
      email: 'student1@school.fr',
      firstname: 'Jean',
      lastname: 'Dupont',
      studentNumber: 'S12345',
      lastLoginTimestamp: new Date('2025-01-15T10:00:00Z'),
      Speciality: {
        name: 'Informatique',
        category: 'Development',
      },
    },
    {
      email: 'student2@school.fr',
      firstname: 'Marie',
      lastname: 'Martin',
      studentNumber: 'S67890',
      lastLoginTimestamp: new Date('2025-01-10T09:30:00Z'),
      speciality: {
        name: 'Gestion de projet',
        category: 'Project managment',
      },
    },
  ];

  const mockJobDemands: JobDemand[] = [
    {
      uid: 'a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8',
      startDate: new Date('2025-09-20'),
      endDate: new Date('2025-09-30'),
      startDayTime: new Date('09:00:00'),
      endDayTime: new Date('17:00:00'),
      creationDatetime: new Date('2025-09-15T14:30:00Z'),
      expirationDays: 30,
      creationTimestamp: new Date('2025-09-15T14:30:00Z'),
      requestor: mockStudents[0],
    },
    {
      uid: 'b2c3d4e5-f6g7-8901-h2i3-j4k5l6m7n8o9',
      startDate: new Date('2025-10-01'),
      endDate: new Date('2025-10-15'),
      startDayTime: new Date('08:30:00'),
      endDayTime: new Date('16:30:00'),
      creationDatetime: new Date('2025-09-16T09:15:00Z'),
      expirationDays: 45,
      creationTimestamp: new Date('2025-09-16T09:15:00Z'),
      requestor: mockStudents[1],
    },
  ];

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [JobDemandController],
      providers: [
        {
          provide: JobDemandService,
          useValue: {
            findAll: jest.fn().mockResolvedValue(mockJobDemands),
            findByStudent: jest
              .fn()
              .mockImplementation((email: string) =>
                Promise.resolve(
                  mockJobDemands.filter((d) => d.requestor.email === email),
                ),
              ),
            findOne: jest
              .fn()
              .mockImplementation((uid: string) =>
                Promise.resolve(
                  mockJobDemands.find((d) => d.uid === uid) || null,
                ),
              ),
            create: jest.fn().mockImplementation((dto: Partial<JobDemand>) => {
              const newDemand = {
                ...dto,
                uid: 'new-generated-uuid-1234-5678-90ab-cdef',
                creationDatetime: new Date(),
                creationTimestamp: Date.now(),
              };
              return Promise.resolve(newDemand);
            }),
            update: jest
              .fn()
              .mockImplementation((uid: string, dto: Partial<JobDemand>) => {
                const existing = mockJobDemands.find((d) => d.uid === uid);
                if (!existing) return Promise.reject(new NotFoundException());
                return Promise.resolve({ ...existing, ...dto, uid });
              }),
            remove: jest.fn().mockImplementation((uid: string) => {
              const exists = mockJobDemands.some((d) => d.uid === uid);
              if (!exists) return Promise.reject(new NotFoundException());
              return Promise.resolve(undefined);
            }),
          },
        },
      ],
    }).compile();

    controller = module.get<JobDemandController>(JobDemandController);
    service = module.get<JobDemandService>(JobDemandService);
  });

  describe('GET /job-demand', () => {
    it('should return an array of job demands with complete student data (200)', async () => {
      const result = await controller.findAll();
      expect(result).toEqual(mockJobDemands);
      expect(service.findAll).toHaveBeenCalled();

      // Verify the structure of returned data
      result.forEach((demand) => {
        expect(demand).toHaveProperty('uid');
        expect(demand).toHaveProperty('requestor');
        expect(demand.requestor).toHaveProperty('email');
        expect(demand.requestor).toHaveProperty('speciality');
        expect(demand.requestor.speciality).toHaveProperty('category');
      });
    });

    it('should return empty array (204) when no demands exist', async () => {
      jest.spyOn(service, 'findAll').mockResolvedValueOnce([]);
      const result = await controller.findAll();
      expect(result).toEqual([]);
    });
  });

  describe('GET /job-demand?studentEmail=:email', () => {
    it('should return job demands for specific student (200)', async () => {
      const studentEmail = 'student1@school.fr';
      const result = await controller.findAll({ studentEmail });
      expect(result).toEqual([mockJobDemands[0]]);
      expect(service.findByStudent).toHaveBeenCalledWith(studentEmail);

      // Verify student data is complete
      expect(result[0].requestor.email).toBe(studentEmail);
      expect(result[0].requestor.speciality.name).toBe('Informatique');
    });

    it('should return empty array (204) when no demands for student', async () => {
      const result = await controller.findAll({
        studentEmail: 'unknown@school.fr',
      });
      expect(result).toEqual([]);
    });
  });

  describe('GET /job-demand/:uid', () => {
    it('should return a single job demand with complete data (200)', async () => {
      const uid = mockJobDemands[0].uid;
      const result = await controller.findOne(uid);

      expect(result).toEqual(mockJobDemands[0]);
      expect(service.findOne).toHaveBeenCalledWith(uid);

      // Verify complete structure
      expect(result).toHaveProperty('startDate');
      expect(result).toHaveProperty('endDate');
      expect(result).toHaveProperty('requestor');
      expect(result.requestor.speciality.category).toBeDefined();
    });

    it('should throw NotFoundException (404) when demand not found', async () => {
      const uid = 'non-existent-uuid';
      await expect(controller.findOne(uid)).rejects.toThrow(NotFoundException);
    });
  });

  describe('POST /job-demand', () => {
    const newDemandDto = {
      startDate: '2025-11-01',
      endDate: '2025-11-15',
      startDayTime: '09:00:00',
      endDayTime: '18:00:00',
      expirationDays: 30,
      requestor: mockStudents[0],
    };

    it('should create a new job demand with generated fields (201)', async () => {
      const result = await controller.create(newDemandDto);

      expect(result).toHaveProperty('uid');
      expect(result.creationDatetime).toBeInstanceOf(Date);
      expect(result.creationTimestamp).toBeDefined();
      expect(service.create).toHaveBeenCalledWith(newDemandDto);

      // Verify requestor data is preserved
      expect(result.requestor.email).toBe(newDemandDto.requestor.email);
    });

    it('should throw BadRequestException (400) for invalid dates', async () => {
      const invalidDto = { ...newDemandDto, startDate: 'invalid-date' };
      jest
        .spyOn(service, 'create')
        .mockRejectedValueOnce(new BadRequestException());
      await expect(controller.create(invalidDto)).rejects.toThrow(
        BadRequestException,
      );
    });

    it('should throw UnauthorizedException (401) when student not authorized', async () => {
      const unauthorizedDto = {
        ...newDemandDto,
        requestor: { email: 'hacker@school.fr' },
      };
      jest
        .spyOn(service, 'create')
        .mockRejectedValueOnce(new UnauthorizedException());
      await expect(controller.create(unauthorizedDto)).rejects.toThrow(
        UnauthorizedException,
      );
    });
  });

  describe('PUT /job-demand/:uid', () => {
    const updateDto = {
      startDate: '2025-10-05',
      endDate: '2025-10-20',
      expirationDays: 35,
    };

    it('should update a job demand (200)', async () => {
      const uid = mockJobDemands[0].uid;
      const result = await controller.update(uid, updateDto);

      expect(result.uid).toBe(uid);
      expect(result.startDate).toBe(updateDto.startDate);
      expect(service.update).toHaveBeenCalledWith(uid, updateDto);
    });

    it('should throw BadRequestException (400) for invalid update data', async () => {
      const uid = mockJobDemands[0].uid;
      const invalidDto = { ...updateDto, startDate: 'invalid-date' };
      jest
        .spyOn(service, 'update')
        .mockRejectedValueOnce(new BadRequestException());
      await expect(controller.update(uid, invalidDto)).rejects.toThrow(
        BadRequestException,
      );
    });

    it('should throw NotFoundException (404) when demand not found', async () => {
      const uid = 'non-existent-uuid';
      await expect(controller.update(uid, updateDto)).rejects.toThrow(
        NotFoundException,
      );
    });

    it('should throw UnauthorizedException (401) when not owner', async () => {
      const uid = mockJobDemands[0].uid;
      jest
        .spyOn(service, 'update')
        .mockRejectedValueOnce(new UnauthorizedException());
      await expect(controller.update(uid, updateDto)).rejects.toThrow(
        UnauthorizedException,
      );
    });
  });

  describe('DELETE /job-demand/:uid', () => {
    it('should delete a job demand (200)', async () => {
      const uid = mockJobDemands[0].uid;
      await expect(controller.remove(uid)).resolves.not.toThrow();
      expect(service.remove).toHaveBeenCalledWith(uid);
    });

    it('should throw NotFoundException (404) when demand not found', async () => {
      const uid = 'non-existent-uuid';
      await expect(controller.remove(uid)).rejects.toThrow(NotFoundException);
    });

    it('should throw UnauthorizedException (401) when not authorized', async () => {
      const uid = mockJobDemands[0].uid;
      jest
        .spyOn(service, 'remove')
        .mockRejectedValueOnce(new UnauthorizedException());
      await expect(controller.remove(uid)).rejects.toThrow(
        UnauthorizedException,
      );
    });
  });
});
