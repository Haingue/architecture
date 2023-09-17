import { BadRequestException, Injectable, Logger } from '@nestjs/common';
import { SupplierDto } from './dto/supplier.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Supplier } from './entities/supplier.entity';
import { DeleteResult, Repository } from 'typeorm';
import { SupplierMapper } from './mapper/supplier.mapper';

@Injectable()
export class SuppliersService {
  private readonly logger = new Logger(SuppliersService.name);
  @InjectRepository(Supplier) // TODO try outside the contructor
  private readonly repository: Repository<Supplier>;

  async create(createSupplierDto: SupplierDto): Promise<SupplierDto> {
    this.logger.log('Create supplier:', createSupplierDto);
    if (!createSupplierDto.code) {
      throw new BadRequestException('The supplier code cannot be empty');
    }
    const existingSupplier: Supplier = await this.repository.findOneBy({
      code: createSupplierDto.code,
    });
    if (existingSupplier) {
      throw new BadRequestException('The supplier code already use');
    }

    let createSupplier: Supplier = new Supplier();
    createSupplier.code = createSupplierDto.code;
    createSupplier.name = createSupplierDto.name;
    createSupplier.address = createSupplierDto.address;
    createSupplier = await this.repository.save(createSupplier);
    return SupplierMapper.entityToDto(createSupplier);
  }

  async findAll(): Promise<SupplierDto[]> {
    this.logger.debug('Find all suppliers');
    const suppliers: Supplier[] = await this.repository.find();
    return suppliers.map((entity) => SupplierMapper.entityToDto(entity));
  }

  async findOne(code: string): Promise<SupplierDto> {
    this.logger.debug('Find one supplier:', code);
    const supplier: Supplier = await this.repository.findOneBy({ code });
    return SupplierMapper.entityToDto(supplier);
  }

  async update(
    code: string,
    updateSupplierDto: SupplierDto,
  ): Promise<SupplierDto> {
    this.logger.debug('Update supplier:', updateSupplierDto);
    let existingSupplier: Supplier = await this.repository.findOneBy({
      code: updateSupplierDto.code,
    });
    if (!existingSupplier) {
      throw new BadRequestException('The supplier does not exist');
    }
    existingSupplier.name = updateSupplierDto.name;
    existingSupplier.address = updateSupplierDto.address;
    existingSupplier = await this.repository.save(existingSupplier);
    return SupplierMapper.entityToDto(existingSupplier);
  }

  async remove(code: string): Promise<DeleteResult> {
    this.logger.debug('Remove supplier:', code);
    const existingSupplier: Supplier = await this.repository.findOneBy({
      code,
    });
    if (!existingSupplier) {
      throw new BadRequestException('The supplier does not exist');
    }
    return this.repository.delete({ code });
  }
}
