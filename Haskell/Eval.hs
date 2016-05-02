{-# LANGUAGE UnicodeSyntax #-}

module Eval where

type CN = Int
type VN = String
data Expr = Const CN
          | Var VN
          | Lambda VN Expr
          | App Expr Expr
          deriving Show

eid     = Lambda "x" $ Var "x"
lid     = \ x → x

ewfv    = Lambda "x" $ Var "y"
-- lwfv    = \x → y -- note "y" is "free"

erone   = App eid (Const 1)
lrone   = (\x → x) 1

erid    = App eid (Lambda "y" $ Var "y")
lrid    = (\x → x) (\y → y)

errone  = App eid erone
lrrone  = (\x → x) ((\x → x) 1)

econst  = Lambda "x" $ Lambda "y" $ Var "x"
lconst  = \x → (\y → x)

etricky = Lambda "y" $ App econst $ Var "y" -- (\y → (\x → \y → x) y)
ltricky = \y → (\x → \y → x) y
whichIsTheSameAs = \y → \z → y

type Subst = VN → Expr

nil :: Subst
nil n = error $ "unknown variable `" ++ n ++ "'"

leave :: Subst
leave n = Var n

snoc :: Subst → VN → Expr → Subst
snoc c n e n' = if n == n' then e else c n'

substitute :: Expr -> Subst -> Expr
substitute (Const c)    _ = Const c
substitute (Var v)      s = s v
substitute (Lambda v a) s = Lambda v $ substitute a $ snoc s v $ Var v
substitute (App a b)    s = App (substitute a s) (substitute b s)

badeval :: Expr → Subst → Expr
badeval (Const c)    _  = Const c
badeval (Var v)      ss = ss v
badeval (Lambda v a) ss = Lambda v $ badeval a $ snoc ss v $ Var v
badeval (App a b)    ss = case (badeval a ss, badeval b ss) of
  (Const c    , e) → Const c `App` e
  (Var v      , e) → Var v   `App` e
  (Lambda v a', e) → badeval a' $ snoc ss v e
  (App a' b'  , e) → (a' `App` b') `App` e

badrun :: Expr → Expr
badrun e = badeval e nil

badrun' :: Expr → Expr
badrun' e = badeval e leave

ewfvtrick = badrun ewfv
ewfvtrick' = badrun' ewfv

etrickytrick = (etricky `App` (Const 1)) `App` (Const 2)
ltrickytrick = ltricky 1 2

badtest = badrun etrickytrick
expected = ltrickytrick

free :: Expr → [VN]
free (Const c)    = []
free (Var v)      = [v]
free (Lambda v a) = filter (not . (== v)) (free a)
free (App a b)    = free a ++ free b

bound :: Expr → [VN]
bound (Const c) = []
bound (Var v)   = []
bound (Lambda v a) = v : bound a
bound (App a b) = bound a ++ bound b

somethingNotIn xs = head . filter (not . (`elem` xs)) . iterate ('\'':) $ "a"

eval :: Expr → Subst → Expr
eval = undefined
