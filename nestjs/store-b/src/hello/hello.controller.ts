import { BadRequestException, Controller, Get, Param } from '@nestjs/common';
import { HelloService } from './hello.service';


@Controller({
    path: "/hello"
})
export class HelloController {
    constructor(private readonly helloService: HelloService) {}

    @Get()
    getHello(): string {
      return this.helloService.getHello();
    }

    @Get('/:name')
    getHelloByName(
        @Param('name')
        name: string
    ) {
        if (name == null || name.trim().length === 0) {
            throw new BadRequestException(`The name is not correct: '${name}'`);
        }
        return this.helloService.getHelloByName(name);
    }
}
