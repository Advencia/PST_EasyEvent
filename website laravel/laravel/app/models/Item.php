<?php 


 class Item extends Eloquent {

 	public function mark() {
       $done = $this->done ? false : true;
       $this->done = $done;
       $this->save();


 	}
 }