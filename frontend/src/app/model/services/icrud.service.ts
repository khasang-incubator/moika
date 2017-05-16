import {BaseMoikaEntity} from "../entities/base-moika-entity";
import {Observable} from "rxjs/Observable";

export interface ICrudService<T extends BaseMoikaEntity> {
  getEntity(id: number): Promise<T>;
  getAll(): Promise<T[]>;
  createEntity<T>(entity: T): Promise<T> ;
  deleteEntity<T>(entity: T): Boolean;
  updateEntity<T>(entity: T): Promise<T> ;
}
