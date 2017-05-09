
import {BaseMoikaEntity} from "./base-moika-entity";

export class SomeStatus extends BaseMoikaEntity {
  private _idStatus: number;
  private _statusCode: string;
  private _statusName: string;

  get idStatus(): number {
    return this._idStatus;
  }

  set idStatus(value: number) {
    this._idStatus = value;
  }

  get statusCode(): string {
    return this._statusCode;
  }

  set statusCode(value: string) {
    this._statusCode = value;
  }

  get statusName(): string {
    return this._statusName;
  }

  set statusName(value: string) {
    this._statusName = value;
  }
}
