import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { WorkspaceService } from 'src/app/services/workspace/workspace.service';
import { Post } from 'src/app/models/post';
import { SecurityService } from 'src/app/services/security/security.service';
import { PreferencesService } from 'src/app/services/preferences/preferences.service';

@Component({
  selector: 'app-workspace',
  templateUrl: './workspace.component.html',
  styleUrls: ['./workspace.component.css']
})
export class WorkspaceComponent implements OnInit {
  posts: Post[];
  workspaceId: number;
  style: any = {};
  
  constructor(private userService: UserService, 
              private workspaceService: WorkspaceService, 
              private securityService: SecurityService, 
              private preferencesService: PreferencesService) { }

  ngOnInit() {
    //this.securityService.getToken();
    this.workspaceService.getPosts();

    this.workspaceService.postchange.subscribe(posts => {
      this.posts = posts;
    });
    this.preferencesService.getPreferences().subscribe(preferences => {
      this.style = { postColor: '', fontColor: '', fontSize: ''};
      this.style.postColor = preferences.find(x => x.preferenceType.name == 'Color').value;
      this.style.fontColor = preferences.find(x => x.preferenceType.name == 'Font Color').value;
      this.style.fontSize = preferences.find(x => x.preferenceType.name == 'Font Size').value;
    })
  }

}
