import { Injectable } from '@nestjs/common';

@Injectable()
export class HelloService {
  getHello(): string {
    return 'Hello World !';
  }
  getHelloByName(name: string): string {
    return `Hello World ${name} !`;
  }
}
