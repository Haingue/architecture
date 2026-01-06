import { HomeModule } from './pages/home/home.module';
import { AuthModule } from './auth/auth.module';
import { UserModule } from './user/user.module';
import { ItemModule } from './item/item.module';
import { Module } from '@nestjs/common';
import { HelloModule } from './hello/hello.module';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PrometheusModule } from "@willsoto/nestjs-prometheus";
import entities from './typeorm';

@Module({
  imports: [
    HomeModule,
    AuthModule,
    ConfigModule.forRoot({
      envFilePath: `${process.env.NODE_ENV}.env`,
      isGlobal: true,
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: (configService: ConfigService) => ({
        type: 'postgres',
        host: configService.get('DB_HOST'),
        port: +configService.get<number>('DB_PORT'),
        username: configService.get('DB_USERNAME'),
        password: configService.get('DB_PASSWORD'),
        database: configService.get('DB_NAME'),
        entities: entities,
        synchronize: true,
      }),
      inject: [ConfigService],
    }),
    PrometheusModule.register(),
    UserModule,
    ItemModule,
    HelloModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
