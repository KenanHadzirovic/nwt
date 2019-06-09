import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Post } from 'src/app/models/post';
import {formatDate} from '@angular/common';
import { PostService } from 'src/app/services/post/post.service';
import { WorkspaceService } from 'src/app/services/workspace/workspace.service';


@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css']
})
export class AddNoteComponent implements OnInit {

  @Input() title: string = "";
  @Input() content: string = "";
  @Input() id: number = 0;

  constructor(public activeModal: NgbActiveModal, private postService: PostService, private workspaceService: WorkspaceService) {}

  ngOnInit() {
  }

  public addPost(){
    var post: Post = {
      id: this.id,
      title: this.title,
      content: this.content,
      isDeleted: false,
      createdDate: new Date(),
      modifiedDate: new Date()
    }
    this.postService.addPost(post).subscribe(() => {
      this.workspaceService.getPosts();
      this.activeModal.close();
    });
  }

}
