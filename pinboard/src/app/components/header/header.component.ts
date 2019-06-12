import { Component, OnInit } from '@angular/core';
import { SecurityService } from 'src/app/services/security/security.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddNoteComponent } from '../modals/add-note/add-note/add-note.component';
import { WorkspaceService } from 'src/app/services/workspace/workspace.service';
import { Workspace } from 'src/app/models/workspace';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  workspaces: Workspace[];
  constructor(private securityService: SecurityService, private router: Router, private modalService: NgbModal, private workspaceService: WorkspaceService) { }

  ngOnInit() {
    this.workspaces = this.workspaceService.getWorkspaces();
  }

  logOut = () => {
    this.securityService.logOut();
    this.router.navigate([ '/login' ]);
  }

  openAddModal() {
    this.modalService.open(AddNoteComponent);
  }
}