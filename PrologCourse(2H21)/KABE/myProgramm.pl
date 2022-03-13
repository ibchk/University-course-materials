:- module(t194041, [t194041/3]).
:- dynamic kaik/5.
:- dynamic parim_kaik/5.
:- dynamic kaalplus/1.
:- dynamic kaalminus/1.


t194041(Status, X, Y) :-
    (Status = 2 -> StatusV is 1,
        StatusVQ is 10,
        StatusQ is 20
    ;   StatusV is 2,
        StatusVQ is 20,
        StatusQ is 10
    ),
    (
        kas_saab_votta_queen_casual(Status,StatusQ,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1) ->
        vota(Status,Xm,Ym,Xv,Yv,Xm1,Ym1)
    ;   leia_parim_kaik(Status,StatusV,StatusVQ,Q,W,E,R),
        writeln("see peaks olema uks kord"),
        retractall(parim_kaik(_,_,_,_,_)),
        retractall(kaik(_,_,_,_,_)),
        tee_kaik(Status,Q,W,E,R)
    ),fail.
t194041(_, _, _).

kas_saab_votta_queen_casual(_,StatusQ,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1) :-
    kas_saab_votta(StatusQ,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1), !.
kas_saab_votta_queen_casual(Status,_,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1) :-
    kas_saab_votta(Status,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1), !.
kas_saab_votta_queen_casual(_,_,_,_,_,_,_,_,_,_) :- fail.

kas_saab_votta(Status,StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1) :-
    ruut(Xm, Ym, Status),
    (
        Status > 5
        ->  check_queen(StatusV,StatusVQ,Xm,Ym,Xv,Yv,Xm1,Ym1)
        ;   (
                Xv is Xm-1, Yv is Ym-1, Xm1 is Xm-2, Ym1 is Ym-2, (ruut(Xv, Yv, StatusV) ; ruut(Xv, Yv, StatusVQ)), ruut(Xm1, Ym1, 0)
            ;   Xv is Xm-1, Yv is Ym+1, Xm1 is Xm-2, Ym1 is Ym+2, (ruut(Xv, Yv, StatusV) ; ruut(Xv, Yv, StatusVQ)), ruut(Xm1, Ym1, 0)
            ;   Xv is Xm+1, Yv is Ym-1, Xm1 is Xm+2, Ym1 is Ym-2, (ruut(Xv, Yv, StatusV) ; ruut(Xv, Yv, StatusVQ)), ruut(Xm1, Ym1, 0)
            ;   Xv is Xm+1, Yv is Ym+1, Xm1 is Xm+2, Ym1 is Ym+2, (ruut(Xv, Yv, StatusV) ; ruut(Xv, Yv, StatusVQ)), ruut(Xm1, Ym1, 0)
            )
    ),!.
kas_saab_votta(_,_,_,_,_,_,_,_,_) :- fail.

check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(1,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(2,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(3,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(4,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(5,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(6,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    check_queen_with_Place(7,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1),!.
check_queen(_,_,_,_,_,_,_,_) :- fail.

check_queen_with_Place(S,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    S2 is S+1, Xq is Xm-S, Yq is Ym-S, Xq1 is Xm-S2, Yq1 is Ym-S2, Xq1 > 0, Xq1 < 9, Yq1 > 0, Yq1 < 9, (ruut(Xq, Yq, StatusV) ; ruut(Xq, Yq, StatusVQ)), ruut(Xq1, Yq1, 0), !.
check_queen_with_Place(S,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    S2 is S+1, Xq is Xm-S, Yq is Ym+S, Xq1 is Xm-S2, Yq1 is Ym+S2, Xq1 > 0, Xq1 < 9, Yq1 > 0, Yq1 < 9, (ruut(Xq, Yq, StatusV) ; ruut(Xq, Yq, StatusVQ)), ruut(Xq1, Yq1, 0), !.
check_queen_with_Place(S,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    S2 is S+1, Xq is Xm+S, Yq is Ym-S, Xq1 is Xm+S2, Yq1 is Ym-S2, Xq1 > 0, Xq1 < 9, Yq1 > 0, Yq1 < 9, (ruut(Xq, Yq, StatusV) ; ruut(Xq, Yq, StatusVQ)), ruut(Xq1, Yq1, 0), !.
check_queen_with_Place(S,StatusV,StatusVQ,Xm,Ym,Xq,Yq,Xq1,Yq1) :-
    S2 is S+1, Xq is Xm+S, Yq is Ym+S, Xq1 is Xm+S2, Yq1 is Ym+S2, Xq1 > 0, Xq1 < 9, Yq1 > 0, Yq1 < 9, (ruut(Xq, Yq, StatusV) ; ruut(Xq, Yq, StatusVQ)), ruut(Xq1, Yq1, 0), !.
check_queen_with_Place(_,_,_,_,_,_,_,_,_) :- fail.

vota(_,Xm,Ym,Xv,Yv,Xm1,Ym1) :-
    ruut(Xm, Ym, MinuStatus),
    ruut(Xv, Yv, StatusV),
    retract(ruut(Xm, Ym, MinuStatus)),
    retract(ruut(Xv, Yv, StatusV)),
    retract(ruut(Xm1, Ym1, 0)),
    assert(ruut(Xm, Ym, 0)),
    assert(ruut(Xv, Yv, 0)),
    (
        (MinuStatus = 1, Xm1 = 8) -> assert(ruut(Xm1, Ym1, 10))
    ;   (MinuStatus = 2, Xm1 = 1) -> assert(ruut(Xm1, Ym1, 20))
    ;   assert(ruut(Xm1, Ym1, MinuStatus))
    ),!.
vota(_,_,_,_,_,_,_).

leia_parim_kaik(Status,StatusV,StatusQV,Qp,Wp,Ep,Rp) :-
    otsi_kaik(Status,StatusV,StatusQV),
    aggregate_all(max(T), kaik(Q,W,E,R,T), MaxT),

%    I is random(4)+1, nth_clause(kaik(_,_,_,_,MaxT), I, R), clause(H, B, R),
%    writeln(H),
    kaik(Qp,Wp,Ep,Rp,MaxT),
    assert(parim_kaik(Qp,Wp,Ep,Rp,MaxT)),!.
leia_parim_kaik(_,_,_,_,_,_,_).

otsi_kaik(Status, StatusV, StatusQV) :-
    ruut(Xm, Ym, Status),
    (
    Status = 1 ->
        (
            Xm1 is Xm+1, Ym1 is Ym-1, ruut(Xm1, Ym1, 0)
        ;   Xm1 is Xm+1, Ym1 is Ym+1, ruut(Xm1, Ym1, 0)
        )
    ;   Status = 2 ->
        (
            Xm1 is Xm-1, Ym1 is Ym-1, ruut(Xm1, Ym1, 0)
        ;   Xm1 is Xm-1, Ym1 is Ym+1, ruut(Xm1, Ym1, 0)
        )
    ;   (
            Xm1 is Xm+1, Ym1 is Ym-1, ruut(Xm1, Ym1, 0)
        ;   Xm1 is Xm+1, Ym1 is Ym+1, ruut(Xm1, Ym1, 0)
        ;   Xm1 is Xm-1, Ym1 is Ym-1, ruut(Xm1, Ym1, 0)
        ;   Xm1 is Xm-1, Ym1 is Ym+1, ruut(Xm1, Ym1, 0)
        )
    ),
%    maplist(write, ['võtame: ',Xm,',',Ym,' ja astume: ',Xm1,',',Ym1]),
    kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV),
    aggregate_all(sum(Kp), kaalplus(Kp), PlusK),
    aggregate_all(sum(Km), kaalminus(Km), MinusK),
    Kaal is PlusK - MinusK,
%    maplist(write, [' !!!!!!!!!!!!!!!!!!!!!!kaaal : ',Kaal, ' minus: ', MinusK, ' plus: ', PlusK]),
    assert(kaik(Xm,Ym,Xm1,Ym1,Kaal)),
    retractall(kaalplus(_)),
    retractall(kaalminus(_)),
%    maplist(write, ['võtame: ',Xm,',',Ym,' ja astume: ',Xm1,',',Ym1]), fail.
otsi_kaik(_,_,_).

kaalu_otsimine(Xm1,Ym1,_,_) :-
    Xm2 is Xm1-1, Ym2 is Ym1-1, ruut(Xm2, Ym2, 0), assert(kaalplus(3)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1-1, Ym2 is Ym1-1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)), assert(kaalminus(2)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1-1, Ym2 is Ym1-1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)), assert(kaalminus(3)), fail.
kaalu_otsimine(Xm1,Ym1,_,_) :-
    Xm2 is Xm1+1, Ym2 is Ym1-1, ruut(Xm2, Ym2, 0),assert(kaalplus(3)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1+1, Ym2 is Ym1-1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)), assert(kaalminus(2)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1+1, Ym2 is Ym1-1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)),assert(kaalminus(3)), fail.
kaalu_otsimine(Xm1,Ym1,_,_) :-
    Xm2 is Xm1-1, Ym2 is Ym1+1, ruut(Xm2, Ym2, 0),assert(kaalplus(3)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1-1, Ym2 is Ym1+1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)),assert(kaalminus(2)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1-1, Ym2 is Ym1+1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)),assert(kaalminus(3)), fail.
kaalu_otsimine(Xm1,Ym1,_,_) :-
    Xm2 is Xm1+1, Ym2 is Ym1+1, ruut(Xm2, Ym2, 0),assert(kaalplus(3)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1+1, Ym2 is Ym1+1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)),assert(kaalminus(2)), fail.
kaalu_otsimine(Xm1,Ym1,StatusV,StatusQV) :-
    Xm2 is Xm1+1, Ym2 is Ym1+1, (ruut(Xm2, Ym2, StatusV);ruut(Xm2, Ym2, StatusQV)),assert(kaalminus(3)), fail.
kaalu_otsimine(_,_,_,_).


tee_kaik(Status,Xm,Ym,Xm1,Ym1) :-
    ruut(Xm, Ym, MinuStatus),
    ruut(Xm1, Ym1, 0),
    retractall(ruut(Xm, Ym, MinuStatus)),
    retractall(ruut(Xm1, Ym1, 0)),
    assert(ruut(Xm, Ym, 0)),
    (
        (Status = 1, Xm1 = 8) -> assert(ruut(Xm1, Ym1, 10))
    ;   (Status = 2, Xm1 = 1) -> assert(ruut(Xm1, Ym1, 20))
    ;   assert(ruut(Xm1, Ym1, MinuStatus))
    ),!.
tee_kaik(_,_,_,_,_).