import { Body, Controller, Delete, Get, HttpException, Inject, Logger, Param, Post, Put, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';
import { Item } from '../typeorm/item.entity';
import { DeleteResult } from 'typeorm';
import { ItemDto } from './dto/item.dto';
import { ItemService } from './item.service';

@Controller({
  
  path: '/service/item'
})
export class ItemController {
  private readonly logger = new Logger(ItemController.name);

  @Inject(ItemService)
  private readonly service: ItemService;

  @Get()
  public getAllItems(): Promise<Item[]> {
    this.logger.log('Load all item');
    return this.service.findAll();
  }

  @Get(':id')
  public getItem(@Param('id') id: bigint): Promise<Item> {
    this.logger.log(`Load one item by id ${id}`);
    return this.service.findOne(id);
  }

  @UseGuards(JwtAuthGuard)
  @Post()
  public createItem(@Body() body: ItemDto): Promise<Item> {
    this.logger.log(`Create new item with name: ${body.name}`);
    return this.service.createItem(body);
  }

  @UseGuards(JwtAuthGuard)
  @Put()
  public updateItem(@Body() body: ItemDto): Promise<Item> {
    this.logger.log(`Update item with name: ${body.name}`);
    return this.service.updateItem(body);
  }

  @UseGuards(JwtAuthGuard)
  @Delete()
  public async deleteItem(@Body() body: ItemDto): Promise<DeleteResult> {
    this.logger.log(`Delete item with id: ${body.id}`);
    try {
      return await this.service.deleteItem(body);
    } catch (error) {
      this.logger.error(`Error to delete item: ${error}`);
      throw error;
    }
  }
}
