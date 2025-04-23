'use client'

import React, { useState } from 'react'
import { Box, Container, HStack, Heading, Button, Flex, Link as ChakraLink, SystemStyleObject, Avatar, AvatarGroup, Menu, Portal, Text, Stack, Icon } from '@chakra-ui/react'
import NextLink from 'next/link'
import { FaSignOutAlt } from "react-icons/fa";

const LinkStyle: SystemStyleObject = {
  base: {
    color: 'fg.muted',
  },
  _hover: {
    color: 'fg',
    textDecoration: 'none'
  }
}

export type HeaderLinkProps = {
  name: string,
  target: string
}

const Header: React.FC = () => {

  const [ isLogined, setLogined ] = useState(true)

  const userLinks: HeaderLinkProps[] = [
    { name: '도서 조회', target: '/book/find' }
  ]

  const adminLinks: HeaderLinkProps[] = [
    { name: '도서 관리', target: '/book/manage' },
    { name: '대출 관리', target: '/checkout/manage' },
    { name: '도서 목록', target: '/book/list' },
  ]

  let role = 'ADMIN'

  return (
    <Flex borderBottomWidth='1px' paddingBlock='4' height='80px' alignItems='center'>
      <Container>
        <HStack justifyContent='space-between'>
          <HStack gap='8'>
            <Heading size='2xl'><NextLink href='/'>Book Manager</NextLink></Heading>
            {isLogined && (
              <>
                {role === 'USER' && (
                  <>
                    {userLinks.map((link, idx) => (
                      <ChakraLink key={idx} css={LinkStyle} asChild>
                        <NextLink href={link.target}>{link.name}</NextLink>
                      </ChakraLink>
                    ))}
                  </>
                )}
                {role === 'ADMIN' && (
                  <>
                    {adminLinks.map((link, idx) => (
                      <ChakraLink key={idx} css={LinkStyle} asChild>
                        <NextLink href={link.target}>{link.name}</NextLink>
                      </ChakraLink>
                    ))}
                  </>
                )}
              </>
            )}
          </HStack>
          <HStack gap='4'>
            {isLogined ? (
              <>
                <Stack alignItems='end' gap='0'>
                  <Stack direction='row' alignItems='baseline'>
                    <Text fontSize='sm'>{role === 'USER' ? '일반' : '관리자'}</Text>
                    <Text>이승훈</Text>
                  </Stack>
                  <Text fontSize='sm' color='fg.muted'>devfriox@gmail.com</Text>
                </Stack>
                <Menu.Root>
                  <Menu.Trigger asChild>
                    <AvatarGroup _hover={{ cursor: 'pointer' }}>
                      <Avatar.Root>
                        <Avatar.Fallback />
                        <Avatar.Image />
                      </Avatar.Root>
                    </AvatarGroup>
                  </Menu.Trigger>
                  <Portal>
                  <Menu.Positioner>
                    <Menu.Content>
                        <Menu.Item value="sign-out" onClick={() => setLogined(false)}>
                          <Stack direction='row' alignItems='center'>
                            <Icon><FaSignOutAlt /></Icon>
                            <Text>로그아웃</Text>
                          </Stack>
                        </Menu.Item>
                      </Menu.Content>
                    </Menu.Positioner>
                  </Portal>
                </Menu.Root>
              </>
            ) : (
              <>
                <HStack gap='4'>
                  <Button variant='outline' onClick={() => setLogined(true)}>Debug Login</Button>
                  <Button variant='outline' asChild><NextLink href='/signin'>로그인</NextLink></Button>
                  <Button asChild><NextLink href='/register'>회원가입</NextLink></Button>
                </HStack>
              </>
            )}
          </HStack>
        </HStack>
      </Container>
    </Flex>
  )
}

export default Header