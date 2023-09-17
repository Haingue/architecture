import { Module } from '@nestjs/common';
import { SuppliersModule } from './suppliers/suppliers.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { HomeModule } from './home/home.module';
import entities from './mytypeorm';
import { PrometheusModule } from '@willsoto/nestjs-prometheus';

const ENV_FILE = process.env.NODE_ENV ? `.env.${process.env.NODE_ENV}` : '.env';

@Module({
  imports: [
    ConfigModule.forRoot({
      envFilePath: ENV_FILE,
      isGlobal: true,
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      inject: [ConfigService],
      useFactory: (configService: ConfigService) => ({
        type: 'postgres',
        host: configService.get('DB_HOST'),
        port: +configService.get<number>('DB_PORT'),
        username: configService.get('DB_USERNAME'),
        password: configService.get('DB_PASSWORD'),
        database: configService.get('DB_NAME'),
        entities: entities,
        synchronize: true, // disable this in production
      }),
    }),
    PrometheusModule.register(),
    SuppliersModule,
    HomeModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
