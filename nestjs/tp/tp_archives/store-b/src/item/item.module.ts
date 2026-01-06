import { ItemService } from './item.service';
import { ItemController } from './item.controller';

import { Module } from '@nestjs/common';
import { Item } from '../typeorm/item.entity';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [TypeOrmModule.forFeature([Item])],
  controllers: [ItemController],
  providers: [ItemService],
  exports: [ItemService]
})
export class ItemModule {}
