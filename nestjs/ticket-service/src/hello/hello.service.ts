import { Injectable, Logger } from '@nestjs/common';

@Injectable()
export class HelloService {
  private readonly logger = new Logger(HelloService.name); // Use default logger

  getHello(): string {
    this.logger.debug('Get hello message');
    return 'Hello World!';
  }
}
