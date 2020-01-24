import { HttpStatus } from './HttpStatus';

export class ErrorModel {
    private message: string;
    private code: string;
    constructor(message: string, code: string) {
        this.code = code;
        this.message = message;
    }

    getMessage(): string {
        return this.message;
    }

    setMessage(message: string): void {
        this.message = message;
    }

    getCode(): string {
        return this.code;
    }

    setCode(code: string): void {
        this.code = code;
    }

    static getErrorAuthentication(status: number): string {
        if (status === HttpStatus.SESSION) {
            return 'La sessión ha expirado';
        } else if (status === HttpStatus.ERR_SV) {
            return 'Lo sentimos, se ha presentado un error en el servidor';
        } else if (status === HttpStatus.ADDRESS_UNREACHABLE) {
            return 'Lo sentimos, no fue posible conectar con el servidor';
        }
    }
}