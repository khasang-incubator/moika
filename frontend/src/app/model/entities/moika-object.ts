import {BaseMoikaEntity} from "./base-moika-entity";

/**
 * type
 * 0 - основные объекты-сущности (car, client, service...)
 * 1- справочники типов
 * 2- справочники статусов
 * 3 - всякие прочие справочники
 */
export class MoikaObject extends BaseMoikaEntity {
  code: string;
  name: string;
  descr: string;
  type: number;
  url: string;
}

