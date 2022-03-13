:- dynamic laevaga/5.
:- dynamic bussiga/5.
:- dynamic rongiga/5.
:- dynamic lennukiga/5.

reisi_otse(X, Y, Tee, Hind) :-
    lennukiga(X, Y, H, _, _),
    Tee = mine(X, Y, lennukiga),
    Hind = H.

otsi_listist([A], A).
otsi_listist([A|B], X) :-
    A == X;
    not(A == X),
    otsi_listist(B, X).


reisi(X, Y, Tee, Hind) :-
    reisi(X, Y, Tee, Hind, []).
reisi(X, Y, Tee, Hind, _) :-
    lennukiga(X, Y, H, _, _),
    Tee = mine(X, Y, lennukiga),
    Hind = H, !.
reisi(X, Y, Tee, Hind, List) :-
	not(otsi_listist(List, X)),
    NewList = [X],
    append(List, NewList, UuendatudList),
    lennukiga(X, Q, H, _, _),
    Q \= Y,
    reisi(Q, Y, NewTee, UusHind, UuendatudList),
    Tee = mine(X, Q, lennukiga, NewTee),
    NewHind is UusHind+H,
    Hind = NewHind.



odavaim_reis(X, Y, Tee, Hind) :-
    not(reisi_otse(X, Y, ReisiTee, ReisiHind)),
    aggregate_all(min(ReisiHind), reisi(X, Y, ReisiTee, ReisiHind), Total),
    Hind = Total,
    reisi(X, Y, ReisiTee, ReisiHind),
    ReisiHind = Total,
    Tee = ReisiTee.
odavaim_reis(X, Y, Tee, Hind) :-
	aggregate_all(min(ReisiHind), reisi_otse(X, Y, ReisiTee, ReisiHind), Total),
    Hind = Total,
    reisi_otse(X, Y, ReisiTee, ReisiHind),
    ReisiHind = Total,
    Tee = ReisiTee.


aegade_vahe(Aeg1, Aeg2, Vahe):-
    time(H1,M1,S1) = Aeg1,
    time(H2,M2,S2) = Aeg2,
    H3 is H2-H1,
    M3 is M2-M1,
    S3 is S2-S1,
    (   (S3 < 0) ->  M4 is M3-1, S4 is 60+S3
    	;   M4 is M3, S4 is S3
    ),
    (   (M4 < 0) ->  H4 is H3-1, M5 is 60+M4
    	;   H4 is H3, M5 is M4
    ),
    (   (H4 < 0) ->  H5 is 24+H4
    	;   H5 is H4
    ),
    (   (S4 > 0) ->  SecToMin is S4/60
    	;   SecToMin is 0
    ),
    M6 is M5+SecToMin,
    HourToMin is 60*H5,
    Vahe is HourToMin+M6.

reisiAjaga(X, Y, Tee, Hind, KoguAeg) :-
    reisiAjaga(X, Y, Tee, Hind, KoguAeg, []).

reisiAjaga(X, Y, Tee, Hind, KoguAeg, _) :-
    lennukiga(X, Y, H, _, _),
    Tee = mine(X, Y, lennukiga),
    KoguAeg is H,
    Hind = H, !.

reisiAjaga(X, Y, Tee, Hind, KoguAeg, List) :-
    lennukiga(X, Q, H, _, Aeg2),
    append(List, [X], UuendatudList),
    not(otsi_listist(List, Q)),
    reisiAjaga(Q, Y, NewTee, UusHind, KoguAeg2, Aeg2, UuendatudList),
    KoguAeg is KoguAeg2+H,
    Tee = mine(X, Q, lennukiga, NewTee),
    NewHind is UusHind+H,
    Hind = NewHind.

reisiAjaga(X, Y, Tee, Hind, KoguAeg, XAeg, _) :-
    lennukiga(X, Y, H, Valjumine, _),
    aegade_vahe(XAeg, Valjumine, Aeg),
    (   (Aeg < 60) ->  UusAeg = Aeg+1440
    	;   UusAeg = Aeg
    ),
    Tee = mine(X, Y, lennukiga),
    KoguAeg is H+UusAeg,
    Hind = H, !.

reisiAjaga(X, Y, Tee, Hind, KoguAeg, XAeg, List) :-
    lennukiga(X, Q, H, Aeg1, Aeg2),
    append(List, [X], UuendatudList),
    not(otsi_listist(List, Q)),
	reisiAjaga(Q, Y, NewTee, UusHind, KoguAeg2, Aeg2, UuendatudList),
    aegade_vahe(XAeg, Aeg1, Vahe),
    (   (Vahe < 60) ->  UusVahe = Vahe+1440
    	;   UusVahe = Vahe
    ),
    KoguAeg is KoguAeg2+H+UusVahe,
    Tee = mine(X, Q, lennukiga, NewTee),
    NewHind is UusHind+H,
    Hind = NewHind.

lyhim_reis(X, Y, Tee, Hind) :-
    aggregate_all(min(KoguAeg), reisiAjaga(X, Y, ReisiTee, ReisiHind, KoguAeg), Total),
    reisiAjaga(X, Y, ReisiTee, ReisiHind, KoguAeg),
    KoguAeg = Total,
    Hind = ReisiHind,
    Tee = ReisiTee.