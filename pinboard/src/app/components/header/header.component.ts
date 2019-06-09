import { Component, OnInit } from '@angular/core';
import { SecurityService } from 'src/app/services/security/security.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddNoteComponent } from '../modals/add-note/add-note/add-note.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private securityService: SecurityService, private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
  }

  logOut = () => {
    this.securityService.logOut();
    this.router.navigate([ '/login' ]);
  }

  openAddModal() {
    this.modalService.open(AddNoteComponent);
  }
}