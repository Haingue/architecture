import { Inject, Injectable, Logger, NotAcceptableException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import * as bcrypt from 'bcrypt';
import { UserService } from '../user/user.service';

@Injectable()
export class AuthService {

  private readonly logger = new Logger(AuthService.name);
  @Inject()
  private readonly userService: UserService;
  @Inject()
  private jwtService: JwtService;

  async validateUser(username: string, password: string): Promise<any> {
    const user = await this.userService.findUsersByUsername(username);
    if (!user) {
      this.logger.debug(`User not found: ${username}`);
      throw new NotAcceptableException('could not find the user');
    }
    const passwordValid = await bcrypt.compare(password, user.password);
    if (user && passwordValid) {
      return user;
    }
    this.logger.debug(`Bad password for the user connection: ${username} (${password} = ${user.password})`);
    return null;
  }

  async login(user: any) {
    this.logger.log('User login in process')
    const payload = { username: user.username, sub: user._id };
    return {
      access_token: this.jwtService.sign(payload),
    };
  }
}
