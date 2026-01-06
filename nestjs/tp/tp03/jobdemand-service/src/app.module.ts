import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { JobDemandModule } from './jobDemand/jobDemand.module';
import { HelloModule } from './hello/hello.module';
import entities from './mytypeorm';

@Module({
  imports: [
    HelloModule,
    JobDemandModule,
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'localhost',
      port: 8093,
      username: 'JobDemandService_USER',
      password: 'kqe!12lsdjconz34aejw',
      database: 'JobDemandService',
      entities: entities,
      synchronize: true,
    }),
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
