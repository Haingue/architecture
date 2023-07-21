import { Module } from '@nestjs/common';
import { HomeController } from './home.controller';
import { ConfigModule } from '@nestjs/config';
import { ItemModule } from '../../item/item.module';

@Module({
  imports: [
    ItemModule,
    ConfigModule.forRoot({ isGlobal: true })
  ],
  controllers: [HomeController],
  providers: [],
})
export class HomeModule {}
