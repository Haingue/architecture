import {
  Body,
  Controller,
  Get,
  Param,
  ParseIntPipe,
  Post,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { CreateUserDto } from './dto/CreateUser.dto';
import { UserDto } from './dto/User.dto';
import { UserService } from './user.service';

@Controller()
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Get('/service/user')
  getUsers() {
    return this.userService.getUsers();
  }

  @Get('/service/user/:username')
  findUsersById(@Param('username') username: string) {
    return this.userService.findUsersByUsername(username);
  }

  @Post('/signup')
  @UsePipes(ValidationPipe)
  createUser(@Body() createUserDto: CreateUserDto) {
    return this.userService.createUser(createUserDto);
  }
}
