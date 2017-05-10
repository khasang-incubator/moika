import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../../entities/client";

@Component({
  selector: 'client-detail',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  @Input() client: Client;

}
