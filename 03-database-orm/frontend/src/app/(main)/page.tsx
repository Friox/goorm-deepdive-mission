import { Container, Stack, Heading, Table, Icon, Text, Stat } from "@chakra-ui/react";
import { FaKey } from "react-icons/fa";

export type BookData = {
  isbn: string,
  title: string,
  author: string,
  date: Date
}

export default function Home() {

  const formatter = new Intl.DateTimeFormat('ko-KR')

  const bookData: BookData[] = [
    { isbn: '9791140710119', title: '스프링 교과서', author: '로렌티우 스필카', date: new Date(2024, 4, 31) },
    { isbn: '9791165922047', title: '스프링으로 시작하는 리액티브 프로그래밍', author: '황정식', date: new Date(2023, 3, 11) },
    { isbn: '9791158395384', title: '스프링 6와 스프링 부트 3로 배우는 모던 API 개발', author: '소라브 샤르마', date: new Date(2024, 11, 5) },
    { isbn: '9791140700448', title: '스프링 마이크로서비스 코딩 공작소', author: '존 카넬, 일러리 후알리루포 산체스', date: new Date(2022, 5, 30) },
    { isbn: '9791191905717', title: '스프링 부트 3 백엔드 개발자 되기: 자바 편', author: '신선영', date: new Date(2024, 3, 5) },
    { isbn: '9788960777330', title: '자바 ORM 표준 JPA 프로그래밍', author: '김영한', date: new Date(2015, 6, 28) },
    { isbn: '9791158395995', title: '스프링 부트 3 핵심 가이드', author: '장정우', date: new Date(2025, 2, 27) },
  ]

  const sortedBookData = bookData.sort((a, b) => +b.date - +a.date)

  return (
    <Container>
      <Stack>
        <Stack mt='12' gap='6'>
          <Stack gap='4'>
            <Text color='colorPalette.fg' fontWeight='semibold'>Introduce</Text>
            <Heading as='h1' size='4xl' fontWeight='bold'>좀 더 편한 도서 관리를 위해</Heading>
          </Stack>
          <Stack>
            <Text fontSize='lg' lineHeight='shorter'>회원 별 대출관리, 도서 목록 관리 등 다양한 기능을 지원합니다.</Text>
            <Text fontSize='lg' lineHeight='shorter'>로그인 하면 더 많은 기능을 사용할 수 있습니다.</Text>
          </Stack>
          <Stack direction='row' gap='16' width='fit-content'>
            <Stat.Root>
              <Stat.Label textWrap='nowrap'>총 회원 수</Stat.Label>
              <Stat.ValueText alignItems='baseline'>1 <Stat.ValueUnit>명</Stat.ValueUnit></Stat.ValueText>
            </Stat.Root>
            <Stat.Root>
              <Stat.Label textWrap='nowrap'>보유 도서 수</Stat.Label>
              <Stat.ValueText alignItems='baseline'>1 <Stat.ValueUnit>권</Stat.ValueUnit></Stat.ValueText>
            </Stat.Root>
          </Stack>
        </Stack>
        <Stack mt='12' gap='3'>
          <Stack direction='row' alignItems='baseline'>
            <Heading>현재 보유 도서 목록</Heading>
            <Text fontSize='sm' color='fg.muted'>출판일 기준 최근 7개 항목</Text>
          </Stack>
          <Table.Root colorPalette='gray' variant='outline' interactive>
            <Table.ColumnGroup>
              <Table.Column htmlWidth='15%'/>
              <Table.Column />
              <Table.Column htmlWidth='20%'/>
              <Table.Column htmlWidth='20%'/>
            </Table.ColumnGroup>
            <Table.Header>
              <Table.Row>
                <Table.ColumnHeader>
                  <Stack direction='row' alignItems='center'>
                    <Icon color='fg.subtle'><FaKey /></Icon>
                    <Text>ISBN</Text>
                  </Stack>
                </Table.ColumnHeader>
                <Table.ColumnHeader>제목</Table.ColumnHeader>
                <Table.ColumnHeader>저자</Table.ColumnHeader>
                <Table.ColumnHeader>출판일</Table.ColumnHeader>
              </Table.Row>
            </Table.Header>
            <Table.Body>
              {sortedBookData.map((book, idx) => (
                <Table.Row key={book.isbn}>
                  <Table.Cell>{book.isbn}</Table.Cell>
                  <Table.Cell>{book.title}</Table.Cell>
                  <Table.Cell>{book.author}</Table.Cell>
                  <Table.Cell>{formatter.format(book.date)}</Table.Cell>
                </Table.Row>
              ))}
            </Table.Body>
          </Table.Root>
        </Stack>
      </Stack>
    </Container>
  )
}
