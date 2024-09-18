import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { PrometheusModule } from '@willsoto/nestjs-prometheus';
import { HelloModule } from './hello/hello.module';

const ENV_FILE = process.env.NODE_ENV ? `.env.${process.env.NODE_ENV}` : '.env';

@Module({
  imports: [
    // Setup configuration from .env files
    ConfigModule.forRoot({
      envFilePath: ENV_FILE,
      isGlobal: true,
    }),
    // Setup prometheus
    PrometheusModule.register(),
    HelloModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
