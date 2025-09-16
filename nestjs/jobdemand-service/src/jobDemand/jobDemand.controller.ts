import { Controller, Post } from '@nestjs/common';
import { JobDemandService } from './JobDemand.service';

@Controller('job-demand')
export class JobDemandController {
  constructor(private readonly jobDemandService: JobDemandService) {}

  @Post()
  publishJobDemand() {
    return 'This action publishes a job demand';
  }
}
