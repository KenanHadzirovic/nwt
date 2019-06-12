import { Component, OnInit } from '@angular/core';
import { WorkspaceService } from 'src/app/services/workspace/workspace.service';
import { Workspace } from 'src/app/models/workspace';

@Component({
  selector: 'app-workspace-panel',
  templateUrl: './workspace-panel.component.html',
  styleUrls: ['./workspace-panel.component.css']
})
export class WorkspacePanelComponent implements OnInit {

  workspaces: Workspace[];
  newWorkspace: Workspace;

  constructor(private workspaceService: WorkspaceService) { }

  ngOnInit() {
    this.newWorkspace = new Workspace();
    this.workspaceService.getWorkspaces();
    this.workspaceService.workspaceChange.subscribe(workspaces => {
      this.workspaces = workspaces;
    });
  }

  removeWorkspace(index:number)
  {
    this.workspaceService.removeWorkspace(this.workspaces[index].id);
  }

  addNew()
  {
    this.workspaceService.addWorkspace(this.newWorkspace).subscribe(obj => {
      this.newWorkspace = new Workspace();
      this.workspaceService.getWorkspaces();
    });
  }

}
