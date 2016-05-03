p a c k a g e   p e r s i s t e n c i a . c o n e x i o n ; 
 
 i m p o r t   j a v a . i o . S e r i a l i z a b l e ; 
 i m p o r t   j a v a . s q l . C o n n e c t i o n ; 
 i m p o r t   j a v a . s q l . D r i v e r M a n a g e r ; 
 
 i m p o r t   d t o . C o n f i g D a t a B a s e D T O ; 
 i m p o r t   p e r s i s t e n c i a . s e r i a l i z a r . S e r i a l i z a d o r B D ; 
 
 p u b l i c   c l a s s   C o n e x i o n   i m p l e m e n t s   S e r i a l i z a b l e   { 
 	 p r i v a t e   s t a t i c   f i n a l   l o n g   s e r i a l V e r s i o n U I D   =   1 L ; 
 	 p u b l i c   s t a t i c   C o n e x i o n   i n s t a n c i a ; 
 	 p r i v a t e   f i n a l   s t a t i c   S t r i n g   d r i v e r   =   " c o m . m y s q l . j d b c . D r i v e r " ; 
 	 p r i v a t e   C o n n e c t i o n   c o n e x i o n ; 
 	 p r i v a t e   s t a t i c   C o n f i g D a t a B a s e D T O   c o n f ; 
 	 p r i v a t e   s t a t i c   b o o l e a n   f a l l o ; 
 
 	 p u b l i c   C o n e x i o n ( )   { 
 	 	 C o n e x i o n . c o n f     =   S e r i a l i z a d o r B D . D e s S e r i a l i z a r ( ) ; 
 	 	 
 	 	 f a l l o   =   f a l s e ; 
 	 	 
 	 	 i f   ( c o n f   ! =   n u l l )   { 
 	 	 	 t r y   { 
 	 	 	 	 C l a s s . f o r N a m e ( d r i v e r ) . n e w I n s t a n c e ( ) ; 
 	 	 	 	 c o n e x i o n   =   D r i v e r M a n a g e r . g e t C o n n e c t i o n ( 
 	 	 	 	 	 	 " j d b c : m y s q l : / / "   +   c o n f . g e t U r l ( )   +   " : "   +   c o n f . g e t P u e r t o ( )   +   " / e l e c t r o _ s e r v i c e _ d b " , 
 	 	 	 	 	 	 " "   +   c o n f . g e t U s u a r i o ( )   +   " " ,   " "   +   c o n f . g e t C o n t r a s e n a ( )   +   " " ) ; 
 	 	 	 }   c a t c h   ( E x c e p t i o n   e )   { 
 
 	 	 	 	 / /   S I   B D   N O   E X I S T E 
 	 	 	 	 i f   ( e . g e t M e s s a g e ( ) . e q u a l s ( " U n k n o w n   d a t a b a s e   ' e l e c t r o _ s e r v i c e _ d b ' " ) )   { 
 	 	 	 	 	 t r y   { 
 	 	 	 	 	 	 / /   C R E O   B A S E   D E   D A T O S 
 	 	 	 	 	 	 C a r g a d o r D a t a B a s e   c a r g a r   =   n e w   C a r g a d o r D a t a B a s e ( c o n f . g e t U r l ( ) ,   c o n f . g e t P u e r t o ( ) , 
 	 	 	 	 	 	 	 	 " e l e c t r o _ s e r v i c e _ d b " ,   c o n f . g e t U s u a r i o ( ) ,   c o n f . g e t C o n t r a s e n a ( ) ) ; 
 
 	 	 	 	 	 	 B o o l e a n   c a r g o   =   c a r g a r . c r e a r D B ( ) ; 
 	 	 	 	 	 	 c a r g a r . c a r g a r ( ) ; 
 	 	 	 	 	 	 / /   P R U E B O   C O N E C T A R M E   D E   N U E V O 
 	 	 	 	 	 	 i f   ( c a r g o )   { 
 	 	 	 	 	 	 	 C l a s s . f o r N a m e ( d r i v e r ) . n e w I n s t a n c e ( ) ; 
 	 	 	 	 	 	 	 c o n e x i o n   =   D r i v e r M a n a g e r . g e t C o n n e c t i o n ( 
 	 	 	 	 	 	 	 	 	 " j d b c : m y s q l : / / "   +   c o n f . g e t U r l ( )   +   " : "   +   c o n f . g e t P u e r t o ( )   +   " / e l e c t r o _ s e r v i c e _ d b " , 
 	 	 	 	 	 	 	 	 	 " "   +   c o n f . g e t U s u a r i o ( )   +   " " ,   " "   +   c o n f . g e t C o n t r a s e n a ( )   +   " " ) ; 
 	 	 	 	 	 	 } 
 	 	 	 	 	 }   c a t c h   ( E x c e p t i o n   e 2 )   { 
 	 	 	 	 	 	 S y s t e m . o u t . p r i n t l n ( e 2 . g e t M e s s a g e ( ) ) ; 
 	 	 	 	 	 	 f a l l o   =   t r u e ; 
 	 	 	 	 	 } 
 	 	 	 	 } 
 	 	 	 	 e l s e 
 	 	 	 	 { 
 	 	 	 	 	 f a l l o   =   t r u e ; 
 	 	 	 	 } 
 	 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ; 
 	 	 	 } 
 	 	 }   e l s e   { 
 	 	 	 f a l l o   =   t r u e ; 
 	 	 	 S y s t e m . o u t . p r i n t l n ( " L a   c o n f i g u r a c i � n   d e   b a s e   d e   d a t o s   n o   e x i s t e . " ) ; 
 	 	 } 
 	 } 
 
 	 p u b l i c   s t a t i c   C o n e x i o n   g e t C o n e x i o n ( )   { 
 	 	 i f   ( i n s t a n c i a   = =   n u l l )   { 
 	 	 	 i n s t a n c i a   =   n e w   C o n e x i o n ( ) ; 
 	 	 } 
 	 	 r e t u r n   i n s t a n c i a ; 
 	 } 
 
 	 p u b l i c   C o n n e c t i o n   g e t S Q L C o n e x i o n ( )   { 
 	 	 r e t u r n   c o n e x i o n ; 
 	 } 
 
 	 p u b l i c   s t a t i c   v o i d   c e r r a r C o n e x i o n ( )   { 
 	 	 i n s t a n c i a   =   n u l l ; 
 	 } 
 	 
 	 p u b l i c   s t a t i c   v o i d   r e c o n e c t a r ( )   { 
 	 	 c e r r a r C o n e x i o n ( ) ; 
 	 	 C o n e x i o n . g e t C o n e x i o n ( ) ; 
 	 } 
 
 	 p u b l i c   s t a t i c   b o o l e a n   i s F a l l o ( )   { 
 	 	 r e t u r n   f a l l o ; 
 	 } 
 } 