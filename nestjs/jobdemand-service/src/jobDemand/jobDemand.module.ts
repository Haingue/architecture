import { Module } from '@nestjs/common';
import { JobDemandService } from './JobDemand.service';
import { JobDemandController } from './jobDemand.controller';
import { Student } from './entities/student.entity';
import { JobDemand } from './entities/jobDemand.entity';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [TypeOrmModule.forFeature([Student, JobDemand])],
  controllers: [JobDemandController],
  providers: [JobDemandService],
  exports: [JobDemandService],
})
export class JobDemandModule {}
