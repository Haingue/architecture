import { Injectable, Logger } from '@nestjs/common';
import { JobDemand } from './entities/jobDemand.entity';
import { Like, Repository } from 'typeorm';

@Injectable()
export class JobDemandService {
  private readonly logger = new Logger(JobDemandService.name);

  constructor(private jobDemandRepository: Repository<JobDemand>) {}

  async publishNewJobDemand(newJobDemand: JobDemand): Promise<JobDemand> {
    this.logger.log(
      `Publishing new job demand: ${JSON.stringify(newJobDemand)}`,
    );
    newJobDemand = await this.jobDemandRepository.save(newJobDemand);
    return newJobDemand;
  }

  async cancelJobDemand(jobDemand: JobDemand): Promise<void> {
    this.logger.log(`Cancelling job demand: ${JSON.stringify(jobDemand)}`);
    await this.jobDemandRepository.delete(jobDemand);
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
