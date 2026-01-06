// job-demand.controller.e2e-spec.ts
import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import request from 'supertest';
import { JobDemand } from './entities/jobDemand.entity';
import { Student } from './entities/student.entity';
import { JobDemandService } from './JobDemand.service';
import { JobDemandModule } from './jobDemand.module';
import { Speciality } from './entities/speciality.entity';

describe('JobDemandController (e2e)', () => {
  let app: INestApplication;

  // Mock data (identique à votre version originale)
  const mockStudents: Student[] = [
    {
      email: 'student1@school.fr',
      firstname: 'Jean',
      lastname: 'Dupont',
      studentNumber: 'S12345',
      lastLoginTimestamp: new Date('2025-01-15T10:00:00Z'),
    },
    {
      email: 'student2@school.fr',
      firstname: 'Marie',
      lastname: 'Martin',
      studentNumber: 'S67890',
      lastLoginTimestamp: new Date('2025-01-10T09:30:00Z'),
    },
  ];

  const mockJobDemands: JobDemand[] = [
    {
      id: 'a1b2c3d4-e5f6-7890-g1h2-i3j4k5l6m7n8',
      title: 'JD1',
      startDate: new Date('2025-09-20'),
      endDate: new Date('2025-09-30'),
      startDayTime: new Date('09:00:00'),
      endDayTime: new Date('17:00:00'),
      creationDatetime: new Date('2025-09-15T14:30:00Z'),
      expirationDays: 30,
      creationTimestamp: new Date('2025-09-15T14:30:00Z'),
      requestor: mockStudents[0],
      speciality: new Speciality(),
    },
    {
      id: 'b2c3d4e5-f6g7-8901-h2i3-j4k5l6m7n8o9',
      title: 'JD2',
      startDate: new Date('2025-10-01'),
      endDate: new Date('2025-10-15'),
      startDayTime: new Date('08:30:00'),
      endDayTime: new Date('16:30:00'),
      creationDatetime: new Date('2025-09-16T09:15:00Z'),
      expirationDays: 45,
      creationTimestamp: new Date('2025-09-16T09:15:00Z'),
      requestor: mockStudents[1],
      speciality: new Speciality(),
    },
  ];

  beforeAll(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [JobDemandModule],
    })
      .overrideProvider(JobDemandService)
      .useValue({
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
          .mockImplementation((id: string) =>
            Promise.resolve(mockJobDemands.find((d) => d.id === id) || null),
          ),
        create: jest.fn().mockImplementation((dto: Partial<JobDemand>) => {
          const newDemand = {
            ...dto,
            id: 'new-generated-uid-1234-5678-90ab-cdef',
            creationDatetime: new Date(),
            creationTimestamp: Date.now(),
          };
          return Promise.resolve(newDemand);
        }),
        update: jest
          .fn()
          .mockImplementation((id: string, dto: Partial<JobDemand>) => {
            const existing = mockJobDemands.find((d) => d.id === id);
            if (!existing) throw new Error('Not Found');
            return Promise.resolve({ ...existing, ...dto, id });
          }),
        remove: jest.fn().mockImplementation((id: string) => {
          const exists = mockJobDemands.some((d) => d.id === id);
          if (!exists) throw new Error('Not Found');
          return Promise.resolve();
        }),
      })
      .compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  describe('GET /job-demand', () => {
    it('should return an array of job demands (200)', () => {
      return request(app.getHttpServer())
        .get('/job-demand')
        .expect(200)
        .expect((res) => {
          expect(Array.isArray(res.body)).toBeTruthy();
          expect((res.body as Array<any>).length).toBe(2);
          (
            res.body as Array<{
              id: string;
              requestor: {
                email: string;
                speciality: { category: string };
              };
            }>
          ).forEach((demand) => {
            expect(demand).toHaveProperty('id');
            expect(demand).toHaveProperty('requestor');
            expect(demand.requestor).toHaveProperty('email');
            expect(demand.requestor.speciality).toHaveProperty('category');
          });
        });
    });

    it('should return empty array when no demands exist (200)', () => {
      return request(app.getHttpServer())
        .get('/job-demand')
        .expect(200)
        .expect((res) => {
          expect(res.body).toEqual([]);
        });
    });
  });

  describe('GET /job-demand?studentEmail=:email', () => {
    it('should return job demands for specific student (200)', () => {
      const studentEmail = 'student1@school.fr';
      return request(app.getHttpServer())
        .get(`/job-demand?studentEmail=${studentEmail}`)
        .expect(200)
        .expect((res) => {
          expect(res.body.length).toBe(1);
          expect(res.body[0].requestor.email).toBe(studentEmail);
          expect(res.body[0].requestor.speciality.name).toBe('Informatique');
        });
    });

    it('should return empty array when no demands for student (200)', () => {
      return request(app.getHttpServer())
        .get('/job-demand?studentEmail=unknown@school.fr')
        .expect(200)
        .expect((res) => {
          expect(res.body).toEqual([]);
        });
    });
  });

  describe('GET /job-demand/:id', () => {
    it('should return a single job demand (200)', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .get(`/job-demand/${id}`)
        .expect(200)
        .expect((res) => {
          expect(res.body.id).toBe(id);
          expect(res.body).toHaveProperty('startDate');
          expect(res.body.requestor.speciality.category).toBeDefined();
        });
    });

    it('should return 404 when demand not found', () => {
      return request(app.getHttpServer())
        .get('/job-demand/non-existent-uid')
        .expect(404);
    });
  });

  describe('POST /job-demand', () => {
    const newDemandDto = {
      title: 'New Job Demand',
      startDate: '2025-11-01',
      endDate: '2025-11-15',
      startDayTime: '09:00:00',
      endDayTime: '18:00:00',
      expirationDays: 30,
      requestor: mockStudents[0],
    };

    it('should create a new job demand (201)', () => {
      return request(app.getHttpServer())
        .post('/job-demand')
        .send(newDemandDto)
        .expect(201)
        .expect((res) => {
          expect(res.body).toHaveProperty('id');
          expect(res.body.creationDatetime).toBeDefined();
          expect(res.body.requestor.email).toBe(newDemandDto.requestor.email);
        });
    });

    it('should return 400 for invalid dates', () => {
      return request(app.getHttpServer())
        .post('/job-demand')
        .send({ ...newDemandDto, startDate: 'invalid-date' })
        .expect(400);
    });

    it('should return 401 when student not authorized', () => {
      return request(app.getHttpServer())
        .post('/job-demand')
        .send({ ...newDemandDto, requestor: { email: 'hacker@school.fr' } })
        .expect(401);
    });
  });

  describe('PUT /job-demand/:id', () => {
    const updateDto = {
      startDate: '2025-10-05',
      endDate: '2025-10-20',
      expirationDays: 35,
    };

    it('should update a job demand (200)', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .put(`/job-demand/${id}`)
        .send(updateDto)
        .expect(200)
        .expect((res) => {
          expect(res.body.id).toBe(id);
          expect(res.body.startDate).toBe(updateDto.startDate);
        });
    });

    it('should return 400 for invalid update data', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .put(`/job-demand/${id}`)
        .send({ ...updateDto, startDate: 'invalid-date' })
        .expect(400);
    });

    it('should return 404 when demand not found', () => {
      return request(app.getHttpServer())
        .put('/job-demand/non-existent-uid')
        .send(updateDto)
        .expect(404);
    });

    it('should return 401 when not owner', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .put(`/job-demand/${id}`)
        .send(updateDto)
        .expect(401);
    });
  });

  describe('DELETE /job-demand/:id', () => {
    it('should delete a job demand (200)', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .delete(`/job-demand/${id}`)
        .expect(200);
    });

    it('should return 404 when demand not found', () => {
      return request(app.getHttpServer())
        .delete('/job-demand/non-existent-uid')
        .expect(404);
    });

    it('should return 401 when not authorized', () => {
      const id = mockJobDemands[0].id;
      return request(app.getHttpServer())
        .delete(`/job-demand/${id}`)
        .expect(401);
    });
  });
});
