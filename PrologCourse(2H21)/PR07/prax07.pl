is_a(roovloomad,elusolend).
is_a(mitte-roovloomad,elusolend).
is_a(veeimetajad,roovloomad).
is_a(kalad,roovloomad).
is_a(saarmas,veeimetajad).
is_a(kobras,veeimetajad).
is_a(ahven,kalad).
is_a(haug,kalad).
is_a(zooplankton,mitte-roovloomad).
is_a(veetaimed,mitte-roovloomad).
is_a(vesikatk,veetaimed).
is_a(vetikas,veetaimed).


eats(zooplankton,veetaimed).
eats(kalad,zooplankton).
eats(veeimetajad,kalad).

count_terminals(Node,Terminals,Count) :-
    findall(Terminal, find_last_terminal(Node,Terminal), Terminals),
    length(Terminals,Count).
find_last_terminal(Node,Terminal) :-
    (is_a(SubClass, Node),
    find_last_terminal(SubClass,Terminal));
    not(is_a(_, Node)),
    Terminal = Node.

extinction(Who,What_spieces,How_many) :-
    not(eats(_,Who)),
    count_terminals(Who, What_spieces, How_many), !.
extinction(Who,What_spieces,How_many) :-
    eats(Eater,Who),
    extinction(Eater,Eater_spieces,_),
    count_terminals(Who, Terminals, _),
    append(Eater_spieces, Terminals, All),
    What_spieces = All,
    length(All,How_many).

find_most_sensitive_species(L, C, T) :-
    aggregate_all(max(Count), extinction(L,_,Count), C),
    extinction(L,T,C).
