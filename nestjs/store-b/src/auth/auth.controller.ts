/*
https://docs.nestjs.com/controllers#controllers
*/

import { Controller, Request, Inject, Post, UseGuards, Logger } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { AuthService } from './auth.service';

@Controller()
export class AuthController {

  @Inject()
  authService: AuthService;

  @UseGuards(AuthGuard('local'))
  @Post('/login')
  async login(@Request() req) {
      return this.authService.login(req.user);
  }

}
