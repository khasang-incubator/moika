import {BaseMoikaEntity} from "../entities/base-moika-entity";

export interface ICrudService<T extends BaseMoikaEntity> {
  getEntity(id: number): Promise<T>;
  getAll(): Promise<T[]>;
  createEntity<T>(entity: T): T;
  deleteEntity<T>(entity: T): Boolean;
  updateEntity<T>(entity: T): T;
}
