'use client'

import { PasswordInput } from '@/components/ui/password-input'
import { Button, Card, Container, Field, Heading, Input, Stack, Text } from '@chakra-ui/react'
import React from 'react'
import { useRouter } from 'next/navigation'

export default function Page() {
  const router = useRouter()

  return (
    <Container width='md' pt='24'>
      <Card.Root variant='elevated' boxShadow='lg'>
        <Card.Header gap='1'>
          <Card.Title>로그인</Card.Title>
          <Card.Description>서비스에 로그인 합니다.</Card.Description>
        </Card.Header>
        <Card.Body>
          <Stack gap='8'>
            <Stack gap='6'>
              <Field.Root>
                <Field.Label>ID</Field.Label>
                <Input />
              </Field.Root>
              <Field.Root>
                <Field.Label>PW</Field.Label>
                <PasswordInput />
              </Field.Root>
            </Stack>
            <Stack gap='4'>
              <Button size='lg'>로그인</Button>
              <Button size='lg' variant='outline' onClick={() => router.replace('/register')}>회원가입</Button>
            </Stack>
          </Stack>
        </Card.Body>
      </Card.Root>
    </Container>
  )
}