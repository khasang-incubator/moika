import {Injectable} from '@angular/core';
import {MockMoikaObjects} from "../entities/mock-moika-objects";
import {MoikaObject} from "../entities/moika-object";

@Injectable()
export class MockMoikaObjectService {

  constructor(private objectListInstance : MockMoikaObjects) {
  }

  getBaseObjects():Promise<MoikaObject[]>  { return this.objectListInstance.getObjects(0)}
  getTypeRefs():Promise<MoikaObject[]>  {return this.objectListInstance.getObjects(1) }
  getStatusRefs():Promise<MoikaObject[]>   { return this.objectListInstance.getObjects(2) }
  getAllObjects():Promise<MoikaObject[]>  {return this.objectListInstance.getObjects(null)};
  getOneObject(code: string):MoikaObject {return this.objectListInstance.getObject(code)};


}

