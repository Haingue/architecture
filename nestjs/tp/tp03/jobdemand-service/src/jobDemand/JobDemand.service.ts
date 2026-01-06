import { Injectable, Logger } from '@nestjs/common';
import { JobDemand } from './entities/jobDemand.entity';
import { Like, Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class JobDemandService {
  private readonly logger = new Logger(JobDemandService.name);

  constructor(
    @InjectRepository(JobDemand)
    private jobDemandRepository: Repository<JobDemand>,
  ) {}

  async publishNewJobDemand(newJobDemand: JobDemand): Promise<JobDemand> {
    this.logger.log(
      `Publishing new job demand: ${JSON.stringify(newJobDemand)}`,
    );
    newJobDemand = await this.jobDemandRepository.save(newJobDemand);
    return newJobDemand;
  }

  async updateExistingJobDemand(
    updatedJobDemand: JobDemand,
  ): Promise<JobDemand> {
    this.logger.log(`Updating job demand: ${JSON.stringify(updatedJobDemand)}`);
    updatedJobDemand = await this.jobDemandRepository.save(updatedJobDemand);
    return updatedJobDemand;
  }

  async cancelJobDemand(id: string): Promise<void> {
    this.logger.log(`Cancelling job demand: ${JSON.stringify(id)}`);
    await this.jobDemandRepository.delete({ id });
  }

  async findOne(id: string): Promise<JobDemand> {
    return this.jobDemandRepository.findOneByOrFail({ id });
  }

  findAllByStudent(studentEmail: string): Promise<JobDemand[]> {
    return this.jobDemandRepository.find({
      where: { requestor: { email: studentEmail } },
      relations: ['requestor', 'speciality'],
    });
  }

  searchByTitle(title: string): Promise<JobDemand[]> {
    return this.jobDemandRepository.find({
      where: { title: Like(`%${title}%`) },
      relations: ['requestor', 'speciality'],
    });
  }
}
