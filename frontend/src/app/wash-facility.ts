export class WashFacility {
  id: number;
  idNet: number;
  name: string = '';
  description: string  = '';
  idAddr:  number;
  idManager: number;

  constructor( values: Object = {} )
  {
    Object.assign( this, values );
  }

}
