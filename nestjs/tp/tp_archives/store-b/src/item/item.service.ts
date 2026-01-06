import { BadRequestException, Injectable, Logger } from '@nestjs/common';
import { DeleteResult, FindManyOptions, Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Item } from '../typeorm';
import { ItemDto } from './dto/item.dto';

@Injectable()
export class ItemService {
    private readonly logger = new Logger(ItemService.name);

    @InjectRepository(Item) // TODO try outside the contructor
    private readonly repository: Repository<Item>;

    async findAll(options?: FindManyOptions<Item>): Promise<Item[]> {
        return this.repository.find(options);
    }

    async findOne(id: bigint): Promise<Item> {
        return this.repository.findOneBy({
            id
        });
    }

    async createItem(itemDto: ItemDto): Promise<Item> {
        if (itemDto.name === null) throw new BadRequestException(null, 'The name cannot be null');
        const existingEntity = await this.repository.findOneBy({ name: itemDto.name })
        if (existingEntity !== null) throw new BadRequestException('This item already exist');

        const itemModel: Item = new Item();
        itemModel.name = itemDto.name;
        itemModel.weight = itemDto.weight;
        itemModel.price = itemDto.price;
        return this.repository.save(itemModel);
    }

    async updateItem(itemDto: ItemDto): Promise<Item> {
        if (itemDto.id === null) throw new BadRequestException(null, 'The id cannot be null');
        if (itemDto.name === null) throw new BadRequestException(null, 'The name cannot be null');
        const existingEntity = await this.repository.findOneBy({ id: itemDto.id })
        if (existingEntity === null) throw new BadRequestException('This item does not exist');

        existingEntity.name = itemDto.name;
        existingEntity.weight = itemDto.weight;
        existingEntity.price = itemDto.price;
        return this.repository.save(existingEntity);
    }

    async deleteItem(itemDto: ItemDto): Promise<DeleteResult> {
        if (itemDto.id === null) throw new BadRequestException('The id cannot be null');
        const existingEntity = await this.repository.findOneBy({ id: itemDto.id })
        if (existingEntity === null) throw new BadRequestException('The item does not exist');
        const result: DeleteResult = await this.repository.delete({
            id: itemDto.id
        })
        this.logger.debug(`Delete ${result.affected} items`)
        return result
    }
}
