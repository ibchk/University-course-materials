female(alika).
female(alisa).
female(liza).
female(oksana).
female(lilia).
female(vasselina).
female(nina).
female(tanja).

female(anna).
female(helen).
female(lucy).
female(malle).

female(olga).

male(ilya).
male(dima).
male(aris).
male(sasha).
male(valentin).
male(aivo).
male(vasja).
male(fedja).
male(leo).
male(kewin).

male(tom).
male(sven).
male(lucas).
male(martin).

married(oksana,sasha). % married(Female, Male).
married(lilia,valentin). % married(Female, Male).
married(vasselina,aivo). % married(Female, Male).
married(nina,vasja). % married(Female, Male).
married(tanja,fedja). % married(Female, Male).
married(anna,tom). % married(Female, Male).
married(helen,sven). % married(Female, Male).
married(lucy,lucas). % married(Female, Male).
married(malle,martin). % married(Female, Male).

mother(alika,oksana). % mother(Child, Mother).
mother(ilya,oksana). % mother(Child, Mother).
mother(liza,vasselina). % mother(Child, Mother).
mother(aris,vasselina). % mother(Child, Mother).
mother(alisa,lilia). % mother(Child, Mother).
mother(dima,lilia). % mother(Child, Mother).
mother(oksana,tanja). % mother(Child, Mother).
mother(lilia,tanja). % mother(Child, Mother).
mother(kewin,nina). % mother(Child, Mother).
mother(leo,nina). % mother(Child, Mother).
mother(sasha,nina). % mother(Child, Mother).
mother(vasselina,nina). % mother(Child, Mother).
mother(tanja,anna). % mother(Child, Mother).
mother(nina,helen). % mother(Child, Mother).
mother(vasja,lucy). % mother(Child, Mother).
mother(fedja,malle). % mother(Child, Mother).


father(X,Y) :- mother(X,Z) , married(Z,Y), male(Y).

brother(X,Y) :- mother(X,Z) , mother(Y,Z) , male(Y) , X \= Y.

sister(X,Y) :- mother(X,Z) , mother(Y,Z) , female(Y) , X \= Y.

aunt(X,Y) :- (mother(X,Z) ; father(X,Z)) , sister(Z,Y).

uncle(X,Y) :- (mother(X,Z) ; father(X,Z)) , brother(Z,Y).

grandfather(X,Y) :- (mother(X,Z) ; father(X,Z)) , father(Z,Y).

grandmother(X,Y) :- (mother(X,Z) ; father(X,Z)) , mother(Z,Y).

parent(X, Y) :- mother(X, Y) ; father(X, Y).


ancestor(Child, Parent) :-
parent(Child, Parent).

ancestor(Child, Parent) :-
parent(Child, Parent2),
ancestor(Parent2, Parent).

male_ancestor(Child, Parent) :-
ancestor(Child, Parent),
male(Parent).

male_ancestor(Child, Parent) :-
father(Child, Father),
male_ancestor(Father, Parent).

female_ancestor(Child, Parent) :-
mother(Child, Parent).

female_ancestor(Child, Parent) :-
mother(Child, Mother),
female_ancestor(Mother, Parent).

ancestor1(Child, Parent, N) :-
N>0,
parent(Child, Parent).

ancestor1(Child, Parent, N) :-
N>0,
parent(Child, Parent1),
ancestor1(Parent1, Parent, N-1).

ancestor2(Child, Parent, X) :-
aggregate_all(count, parent(_, Child), Y),
Y > X,
Parent is Child.

ancestor2(Child, Parent, X) :-
parent(Child, Parent1),
ancestor2(Parent1, Parent, X).