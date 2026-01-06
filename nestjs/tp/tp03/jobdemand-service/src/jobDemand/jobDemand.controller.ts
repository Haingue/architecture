import {
  Body,
  Controller,
  Delete,
  Get,
  HttpStatus,
  Param,
  Post,
  Put,
  Query,
  Res,
} from '@nestjs/common';
import { JobDemandService } from './JobDemand.service';
import { JobDemand } from './entities/jobDemand.entity';
import express from 'express';

@Controller('job-demand')
export class JobDemandController {
  constructor(private readonly jobDemandService: JobDemandService) {}

  @Get(':id')
  getJobDemandById(@Param('id') id: string, @Res() res: express.Response) {
    const jobDemand: Promise<JobDemand> = this.jobDemandService.findOne(id);
    res.status(HttpStatus.OK).json(jobDemand);
    return res;
  }

  @Get()
  getAllJobDemandsByStudent(
    @Res() res: express.Response,
    @Query('title') title?: string,
    @Query('page') page?: number,
    @Query('size') size?: number,
  ) {
    const jobDemands: Promise<JobDemand[]> =
      this.jobDemandService.searchByTitle(title || '*');
    res.status(HttpStatus.OK).json(jobDemands);
    return res;
  }

  @Post()
  publishJobDemand(
    @Body() newJobDemand: JobDemand,
    @Res() res: express.Response,
  ) {
    const savedJobDemand =
      this.jobDemandService.publishNewJobDemand(newJobDemand);
    res.status(HttpStatus.CREATED).json(savedJobDemand);
    return res;
  }

  @Put()
  updateJobDemand(
    @Body() updatedJobDemand: JobDemand,
    @Res() res: express.Response,
  ) {
    const savedJobDemand: Promise<JobDemand> =
      this.jobDemandService.updateExistingJobDemand(updatedJobDemand);
    res.status(HttpStatus.OK).json(savedJobDemand);
    return res;
  }

  @Delete(':id')
  async cancelJobDemand(@Param('id') id: string, @Res() res: express.Response) {
    await this.jobDemandService.cancelJobDemand(id);
    return res;
  }
}
