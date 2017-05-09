import {BaseMoikaEntity} from "./base-moika-entity";
import {Phone} from "./phone";

export class Person extends BaseMoikaEntity {
  private _idPerson: number;
  private _firstName: string;
  private _middleName: string = '';
  private _lastName: string;
  private _birthDate: Date;
  private _phones: Phone[];
  private _email: string = '';

  get idPerson(): number {
    return this._idPerson;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get middleName(): string {
    return this._middleName;
  }

  set middleName(value: string) {
    this._middleName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  get birthDate(): Date {
    return this._birthDate;
  }

  set birthDate(value: Date) {
    this._birthDate = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get phones(): Phone[] {
    return this._phones;
  }

  set phones(value: Phone[]) {
    this._phones = value;
  }
}
