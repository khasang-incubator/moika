import { Component, OnInit } from '@angular/core';
import {Client} from "../../model/entities/client";
import {ClientService} from "../../model/services/client.service";
import {CrudService} from "../../model/services/crud.service";
import {ICrudService} from "../../model/services/icrud.service";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clientList: Array<Client> = [];
  selectedClient: Client;
  clientService: ClientService<Client>;

  constructor(clientService: ClientService<Client>) {
    this.clientService = clientService;
  }

  getAll(): void {
    this.clientService.getAll().then(clientList => this.clientList = clientList).catch(this.handleError);
  }

  ngOnInit(): void {
    this.getAll();
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список клиентов. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
