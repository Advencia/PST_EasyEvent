@extends('layouts.utilisateur')

@section('content')
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <h1>Vos Taches <small> <a href="{{ URL::route('new') }}" class="btn btn-primary add_button">Nouvelle Tache</a></small></h1>

   <div class="table-responsive">
        <table class="table table-bordered table-striped">
 
            <thead>
                <tr>
                    
                    <th>name</th>
                    <th>Email</th>
                    
                    <th></th>
                </tr>
            </thead>
 
            <tbody>
                @foreach ($users as $user)
                <tr>
                    
                    <td>{{ $user->name }}</td>
                    <td>{{ $user->email }}</td>
                    
                    <td>
                        <a href="#" class="btn btn-info pull-left" style="margin-right: 3px;">Edit</a>
                        {{ Form::open(['url' => '/user/' . $user->id, 'method' => 'DELETE']) }}
                        {{ Form::submit('Delete', ['class' => 'btn btn-danger'])}}
                        {{ Form::close() }}
                    </td>
                </tr>
                @endforeach
            </tbody>
 
        </table>
    </div>
 
@stop