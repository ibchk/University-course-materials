laevaga(tallinn, helsinki, 120).
laevaga(tallinn, stockholm, 480).
laevaga(stockholm, tallinn, 480).
bussiga(tallinn, riia, 300).
rongiga(riia, berlin, 680).
lennukiga(tallinn, helsinki, 30).
lennukiga(helsinki, paris, 180).
lennukiga(paris, berlin, 120).

otsi_listist([A], A).
otsi_listist([A|B], X) :-
    A == X;
    not(A == X),
    otsi_listist(B, X).

reisi_otse(X, Y, Tee, Transport) :-
    laevaga(X, Y, Tee),
    Transport = laevaga.
reisi_otse(X, Y, Tee, Transport) :-
    bussiga(X, Y, Tee),
    Transport = bussiga.
reisi_otse(X, Y, Tee, Transport) :-
    rongiga(X, Y, Tee),
    Transport = rongiga.
reisi_otse(X, Y, Tee, Transport) :-
    lennukiga(X, Y, Tee),
    Transport = lennukiga.

reisi(X, Y) :-
    reisi_otse(X, Y, _, _), !.
reisi(X, Y) :-
    laevaga(X, Q, _),
    reisi(Q, Y), !.
reisi(X, Y) :-
    bussiga(X, Q, _),
    reisi(Q, Y), !.
reisi(X, Y) :-
    rongiga(X, Q, _),
    reisi(Q, Y), !.
reisi(X, Y) :-
    lennukiga(X, Q, _),
    reisi(Q, Y), !.

reisi(X, Y, Tee) :-
    reisi_maluga(X, Y, Tee, []).
reisi_maluga(X, Y, Tee, _) :-
    reisi_otse(X, Y, _, _),
    Tee = mine(X, Y), !.
reisi_maluga(X, Y, Tee, List) :-
    not(otsi_listist(List, X)),
    NewList = [X],
    append(List, NewList, UuendatudList),
    reisi_otse(X, Q, _, _),
    Q \= Y,
    reisi_maluga(Q, Y, NewTee, UuendatudList),
    Tee = mine(X, Q, NewTee).

reisi_transpordiga(X, Y, Tee) :-
    reisi_transpordiga(X, Y, Tee, []).
reisi_transpordiga(X, Y, Tee, _) :-
    reisi_otse(X, Y, _, Transport),
    Tee = mine(X, Y, Transport), !.
reisi_transpordiga(X, Y, Tee, List) :-
    not(otsi_listist(List, X)),
    NewList = [X],
    append(List, NewList, UuendatudList),
    reisi_otse(X, Q, _, Transport),
    Q \= Y,
    reisi_transpordiga(Q, Y, NewTee, UuendatudList),
    Tee = mine(X, Q, Transport, NewTee).


reisi(X, Y, Tee, Hind) :-
    reisi(X, Y, Tee, Hind, []).
reisi(X, Y, Tee, Hind, _) :-
    reisi_otse(X, Y, Hind, Transport),
    Tee = mine(X, Y, Transport).
reisi(X, Y, Tee, Hind, List) :-
    not(otsi_listist(List, X)),
    NewList = [X],
    append(List, NewList, UuendatudList),
    reisi_otse(X, Q, H, Transport),
    reisi(Q, Y, NewTee, UusHind, UuendatudList),
    Tee = mine(X, Q, Transport, NewTee),
    plus(H, UusHind, NewHind),
    Hind = NewHind.