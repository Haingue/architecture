import { SupplierDto } from '../dto/supplier.dto';
import { Supplier } from '../entities/supplier.entity';

export class SupplierMapper {
  static dtoToEntity(dto: SupplierDto): Supplier {
    const entity: Supplier = new Supplier();
    entity.code = dto.code;
    entity.name = dto.name;
    entity.address = dto.address;
    return entity;
  }

  static entityToDto(entity: Supplier): SupplierDto {
    const dto: SupplierDto = new SupplierDto();
    dto.code = entity.code;
    dto.name = entity.name;
    dto.address = entity.address;
    return dto;
  }
}
