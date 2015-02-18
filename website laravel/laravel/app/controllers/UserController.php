<?php 
 class UserController extends BaseController {

    public function _construct()
    {
    	$this->beforeFilter('auth');
    }

    public function index()
    {
    	$users = User::all();
    	return View::make('user.index',['users' => $users]);

    }

    public function create()
    {
    	return View::make('user.create');
    }

    public function store()
    {
    	$user =new User;

    	$user->first_name = Input::get('first_name');
    	$user->last_name = Input::get('last_name');
    	$user->username = Input::get('username');
    	$user->email = Input::get('email');
    	$user->password = Hash::make(Input::get('password'));
        $user->validation = "nom";
    	$user->save();

    	return Redirect::to('/user');


    }

    public function edit($id)
    {
    	$user = User::find($id);
    	return View::make('edit', [ 'user' => $user ]);
    }

    public function update($id)
    {
    	$user = User::find($id);
    	$user->first_name = Input::get('first_name');
    	$user->last_name = Input::get('last_name');
    	$user->username = Input::get('username');
    	$user->email = Input::get('email');
    	$user->password = Hash::make(Input::get('password'));

    	$user->save();

    	return Redicrect::to('/user');
    }

     public function destroy($id)
    {
        User::destroy($id);
 
        return Redirect::to('/user');
    }

}
 