import { TestBed, inject } from '@angular/core/testing';

import { ClientService } from './client.service';
import {Client} from "../entities/client";


describe('ClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClientService]
    });
  });

  it('should ...', inject([ClientService], (service: ClientService<Client>) => {
    expect(service).toBeTruthy();
  }));
});
