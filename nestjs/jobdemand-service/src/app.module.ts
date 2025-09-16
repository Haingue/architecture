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
      port: 5432,
      username: 'root',
      password: 'root',
      database: 'test',
      entities: entities,
      synchronize: true,
    }),
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
