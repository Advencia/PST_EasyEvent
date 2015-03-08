@extends('layout.master')
@section('title') Create USer @stop
@section('content')

<div class='col-lg-4 col-lg-offset-4'>
 
  @if ($errors->has())
    @foreach ($errors->all() as $error)
      <div class='bg-danger alert'>{{ $errors }}</div>
    @endforeach
  @endif

  <h1><i class='fa fa-user'></i> Add User</h1>

  {{ Form::open(['role' => 'form', 'url' => '/user']) }}

  <div class='form-group'>
    {{ Form::label('first_name', 'First Name')}}
    {{ Form::text('first_name', null, ['placeholder' => 'First Name', 'class' => 'form-control']) }}
    </div>
  
  <div class='form-group'>
    {{ Form::label('last_name', 'Last Name') }}
    {{ Form::text('last_name', null, ['placeholder' => 'Last Name', 'class' => 'form-control']) }}
  </div>

  <div class='form-group'>
    {{ Form::label('username', 'Username') }}
    {{ Form::text('username', null, ['placeholder' => 'Username', 'class' => 'form-control']) }}
  </div>

  <div class='form-group'>
    {{ Form::label('email', 'Email') }}
    {{ Form::text('email', null, ['placeholder' => 'Email', 'class' => 'form-control']) }}
  </div>

  <div class='form-group'>
    {{ Form::label('password', 'Password') }}
    {{ Form::text('password', null, ['placeholder' => 'Password', 'class' => 'form-control']) }}
  </div>

  <div class='form-group'>
    {{ Form::label('password_confirmation', 'Confirm Password') }}
    {{ Form::text('password_confirmation', null, ['placeholder' => 'Confirm PAssword', 'class' => 'form-control']) }}
  </div>

  <div class='form-group'>
    {{ Form::submit('login', ['class' => 'btn btn-primary' ]) }}
  </div>

  {{ Form::close() }}

 </div>
 @stop 
