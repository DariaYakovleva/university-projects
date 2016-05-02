{-# LANGUAGE NoImplicitPrelude #-}

module Functions(($), at, (.), (.>), on, ($$)) where

infixr 0 $
($) :: (a -> b) -> (a -> b)
f $ x = f x

-- f = g $ h x -- f = g (h x)

at :: a -> (a -> b) -> b
x `at` f = f x

-- f = x `at` g -- f = g x
  
infixr 9 .
(.) :: (b -> c) -> (a -> b) -> (a -> c)
(.) f g x = f (g x)

-- f = g . h -- f x = g (h x)

-- In default library: >>>
(.>) :: (c -> d) -> (a -> c) -> (a -> d)
g .> f = g . f

-- f = h .> g -- f x = g (h x)

infixl 0 `on`
on :: (b -> b -> c) -> (a -> b) -> a -> a -> c
on f g = (.g). (f . g) 
--on f g x y = f (g x) (g y)

-- h = f `on` g

($$) :: (c -> d) -> (a -> b -> c) -> (a -> b -> d)
-- ($$) g f x y = g $ f x y 
-- ($$) g f x = g . (f x) 
($$) = (.) . (.)
