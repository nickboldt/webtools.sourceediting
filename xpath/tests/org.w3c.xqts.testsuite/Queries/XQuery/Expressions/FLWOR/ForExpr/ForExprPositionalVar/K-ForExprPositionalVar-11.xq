(:*******************************************************:)
(: Test: K-ForExprPositionalVar-11                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Verify that the position is properly computed for the empty sequence. :)
(:*******************************************************:)
empty(for $i at $p in () return $p)