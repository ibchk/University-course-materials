viimane_element(_, []) :- fail. 
viimane_element(X,[Z]) :-
    X = Z.
viimane_element(X,[_|Z]) :-
    viimane_element(X,Z).


suurim([], _) :-
    true.
suurim([A|B], X) :-
    suurim(A, B, [], X).
suurim(Previous, [], List, X) :- 
    L = [Previous],
    append(List, L, NewList),
    X = NewList.
suurim(Previous, [A|B], List, X) :-
    (   (A = []; (Previous \= [], Previous > A)) ->  
        L = [Previous]
    ;   L = [A]
    ),
    append(List, L, NewList),
    suurim(A, B, NewList, X).


paki([],_) :- true.
paki([First|Tail],X) :-
    L = [First],
    paki(First, Tail, L, X).
paki(_, [], List, X) :- 
    X = List.
paki(First, [Second|Tail], List, X) :-
    First \= Second,
    L = [Second],
    append(List, L, NewList),
    paki(Second, Tail, NewList, X).
paki(First, [Second|Tail], List, X) :-
    First = Second,
    paki(Second, Tail, List, X).


duplikeeri([],_) :- true.
duplikeeri([First|Tail],X) :-
    L = [First, First],
    duplikeeri(Tail, L, X).
duplikeeri([], L, X) :-
    X = L.
duplikeeri([First|Tail], List, X) :-
	L = [First, First],
	append(List, L, NewList),
    duplikeeri(Tail, NewList, X).


kordista([], _, _) :- true.
kordista(List, Factor, X) :-
    kordista(List, Factor, [], X).
kordista([], _, L, X) :-
    X = L.
kordista([First|Tail], Factor, List, X) :-
    FirstList = [First],
	add(Factor, FirstList, [], L),
    append(List, L, NewList),
    kordista(Tail, Factor, NewList, X).

add(0, _, List, L) :-
    L = List.
add(Factor, X, List, L) :-
    Factor>0,
   	append(List, X, NewList),
    S is Factor-1, 
    add(S, X, NewList, L).


vordle_predikaadiga([], _, X) :-
    X = [].
vordle_predikaadiga(List, [paaritu_arv], X) :-
    paaritu_arv(List, [], X).
vordle_predikaadiga(List, [paaris_arv], X) :-
    paaris_arv(List, [], X).
vordle_predikaadiga(List, [P|Nr], X) :-
    P = suurem_kui,
    suurem_kui(List, [], Nr, X).

paaritu_arv([], List, X) :-
    X = List.
paaritu_arv([First|Tail], List, X) :-
    0 is mod(First, 2),
    paaritu_arv(Tail, List, X).
paaritu_arv([First|Tail], List, X) :-
    1 is mod(First, 2),
    FirstList = [First],
    append(List, FirstList, NewList),
    paaritu_arv(Tail, NewList, X).

paaris_arv([], List, X) :-
    X = List.
paaris_arv([First|Tail], List, X) :-
    1 is mod(First, 2),
    paaris_arv(Tail, List, X).
paaris_arv([First|Tail], List, X) :-
    0 is mod(First, 2),
    FirstList = [First],
    append(List, FirstList, NewList),
    paaris_arv(Tail, NewList, X).

suurem_kui([], List, _, X) :-
    X = List.
suurem_kui([First|Tail], List, Nr, X) :-
    First =< Nr,
    suurem_kui(Tail, List, Nr, X).
suurem_kui([First|Tail], List, Nr, X) :-
    First > Nr,
    FirstList = [First],
    append(List, FirstList, NewList),
    suurem_kui(Tail, NewList, Nr, X).